import { Component, OnInit } from '@angular/core';
import Event from '../../utillity/Event';
import { ProjectTemplate } from '../../model/project-template';

@Component({
  selector: 'project-creation',
  templateUrl: './project-creation.component.html',
  styleUrls: ['./project-creation.component.css']
})
export class ProjectCreationComponent implements OnInit {

  private templates: ProjectTemplate[];

  constructor() {
    this.templates = [
      {
        id: "Empty",
        content: ''
      },
      {
        id: "HelloWorld",
        content: 'public static void main(String[] args){\n\tSystem.out.println("Hello World!")\n}'
      }
    ]
  }

  ngOnInit() {
    Event.listen("headerReady", () => {
      Event.emit("userChanged", "Henk de Vries")
    })
  }
}
