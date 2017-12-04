import { Component, OnInit, ViewChild } from '@angular/core';
import { AceEditorComponent } from 'ng2-ace-editor';  

@Component({
  selector: 'editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css'],
})
export class EditorComponent implements OnInit {

  private projectId: String;
  @ViewChild('editor') editor: AceEditorComponent;

  constructor() { }

  ngOnInit() {
    
  }

  onChange(data) {
    console.log
  }
}
