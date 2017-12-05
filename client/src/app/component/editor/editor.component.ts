import { Component, OnInit, ElementRef, Input, HostListener, ViewChild, AfterViewInit } from '@angular/core';
import $ from 'jquery';
import { ConfigService } from '../../service/config.service';
import { StompService } from 'ng2-stomp-service';
import { AceEditorComponent } from 'ng2-ace-editor';
import { Router, ActivatedRoute, Params } from '@angular/router';
import Event from '../../utillity/Event';
import TextUtillity from '../../utillity/text.utillity';
import Context from '../../utillity/Context';

@Component({
  selector: 'editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css'],
  providers: [ConfigService]
})
export class EditorComponent implements OnInit {

  isLoaded: boolean;
  config: any;
  content: String = "";
  previousContent: String;
  projectId: String = undefined;
  clientId = Context.currentUser.username;

  constructor(private configurer: ConfigService, private stomp: StompService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    let isTemplate;
    this.route.queryParams.subscribe(param => {
      if (param["isTemplate"]) {
        const projectId = prompt("Vul een projectnaam in.");

        if (projectId === null || projectId === "") {
          this.router.navigate(['project-creation']);
        } else {
          this.projectId = projectId;
          this.content = param["content"].toString();
          isTemplate = true;
        }
      } else {
        this.projectId = param['project']
      }
    });

    Event.emit("projectNameChanged", this.projectId);
    Event.listen("compile", () => console.log("Compile", this.content))

    this.configurer.get().subscribe(data => {
      this.config = data.json();

      const base = this.config.host.local.base;
      const websocketPort = this.config.ports.websocket;
      const websocketName = this.config.host.local.websocketName;

      this.stomp.configure({
        host: base + websocketPort + websocketName,
        queue: ''
      });

      this.stomp.startConnect()
        .then(() => this.configureSubscriptions())
        .then(() => {this.stomp.send("/syncoder/project/onOpened", {
          clientId: this.clientId,
          projectId: this.projectId,
          username: this.clientId
        })})
        .then(() => this.isLoaded = true)
    });
  }

  @HostListener('window:beforeunload', ['$event'])
  beforeunloadHandler(event) {
    this.stomp.send("/syncoder/project/onClosed", { clientId: this.clientId, projectId: this.projectId });
  }

  onChange(newContent) {
    if (newContent !== this.previousContent) {
      this.previousContent = this.content;

      this.stomp.send("/syncoder/project/change/" + this.projectId, {
        id: this.projectId,
        content: newContent
      })
    }
  }

  configureSubscriptions() {
    const subscriptions = this.config.host.local.subscriptions;

    this.stomp.subscribe(subscriptions.project.onClientCountChange + this.projectId, (data) => {
      this.content = data.content;
      this.previousContent = data.content;
      Event.emit('connectedClientsChanged', data.clients)
    })

    this.stomp.subscribe(subscriptions.project.onChange + this.projectId, (project) => {
      this.content = project.content;
    })
  }
}
