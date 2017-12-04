import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { StompService } from 'ng2-stomp-service';
import { HeaderComponent } from './component/header/header.component';
import { EditorComponent } from './component/editor/editor.component';
import { LoginComponent } from './component/login/login.component';
import { ProjectManagerComponent } from './component/project-manager/project-manager.component';
import { AceEditorComponent } from 'ng2-ace-editor'; 

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    EditorComponent,
    LoginComponent,
    ProjectManagerComponent,
    AceEditorComponent
  ],
  imports: [
    BrowserModule,
  ],
  providers: [
    StompService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
