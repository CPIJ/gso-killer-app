import { Routes } from "@angular/router";
import { EditorpageComponent } from "./component/editorpage/editorpage.component";
import { ProjectCreationComponent } from "./component/project-creation/project-creation.component";
import { LoginComponent } from "./component/login/login.component";
import { RegisterComponent } from "./component/register/register.component";
import { AuthorizationGuard } from "./security/authorization-guard";

export const routes: Routes = [
    { component: EditorpageComponent,  path: 'editor' },
    { component: ProjectCreationComponent, path: 'project-creation', canActivate: [AuthorizationGuard] },
    { component: LoginComponent, path: '' },
    { component: RegisterComponent, path: 'register' }
  ]