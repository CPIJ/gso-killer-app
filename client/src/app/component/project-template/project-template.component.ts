import { Component, OnInit, Input } from '@angular/core';
import { ProjectTemplate } from '../../model/project-template';
import Context from '../../utillity/Context';
import { User } from '../../model/user';
import { Router } from '@angular/router';

@Component({
  selector: 'project-template',
  templateUrl: './project-template.component.html',
  styleUrls: ['./project-template.component.css']
})
export class ProjectTemplateComponent implements OnInit {

  @Input() id: String;
  @Input() content: String;

  constructor(private router: Router) {
  }

  ngOnInit() {

  }

  createNew() {
    this.router.navigate(['editor'], { queryParams: { project: this.id, isTemplate: true, content: this.content } });
  }
}
