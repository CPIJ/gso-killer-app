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
import { ProjectCreationComponent } from './component/project-creation/project-creation.component';
import { RegisterComponent } from './component/register/register.component';
import { LoginComponent } from './component/login/login.component';
import { FormsModule } from '@angular/forms';

const routes: Routes = [
  { component: EditorpageComponent, path: 'editor' },
  { component: ProjectCreationComponent, path: 'project-creation' },
  { component: LoginComponent, path: '' },
  { component: RegisterComponent, path: 'register' }
]

@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    EditorComponent,
    EditorpageComponent,
    ProjectCreationComponent,
    RegisterComponent,
    LoginComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    RouterModule.forRoot(routes, { useHash: true }),
    NgbModule.forRoot(),
    FormsModule,
    AceEditorModule
  ],
  providers: [
    StompService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

