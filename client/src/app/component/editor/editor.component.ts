import { Component, OnInit, ElementRef } from '@angular/core';
import $ from 'jquery';

@Component({
  selector: 'editor',
  templateUrl: './editor.component.html',
  styleUrls: ['./editor.component.css']
})
export class EditorComponent implements OnInit {

  content = 'int x = 12;';

  constructor() { }

  ngOnInit() {
  }

  onChange(event) {
    console.log(this.content);
  }
}
