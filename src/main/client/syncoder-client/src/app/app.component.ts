import { Component } from '@angular/core';
import { StompService } from "ng2-stomp-service";
import { host, subscriptions } from './config/WebsocketConfig';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  private websocketConfig = {
    host: host,
    queue: ''
  }

  title = 'app';
  projectId = "HelloWorld"

  constructor(private stomp: StompService) {
    stomp.configure(this.websocketConfig);

    stomp.startConnect()
      .then(() => {
        stomp.subscribe(subscriptions.projectOnChange + this.projectId, (response) => {
          console.log(response);
        })
      })
  }
}
