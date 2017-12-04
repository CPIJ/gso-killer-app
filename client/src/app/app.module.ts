import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { AppComponent } from './app.component';
import { HeaderComponent } from './component/header/header.component';
import { EditorComponent } from './component/editor/editor.component';
import { AceEditorModule } from 'ng2-ace-editor';
import { Http, HttpModule } from '@angular/http';

import { StompService } from 'ng2-stomp-service';
import { RouterModule, Routes } from '@angular/router';
import { EditorpageComponent } from './component/editorpage/editorpage.component';

const routes: Routes = [
  { component: EditorpageComponent, path: 'editor' }
] 

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    EditorComponent,
    EditorpageComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(routes, { useHash: true }),
    NgbModule.forRoot(),
    AceEditorModule
  ],
  providers: [
    StompService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

