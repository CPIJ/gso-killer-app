/* SystemJS module definition */
declare var module: NodeModule
declare module 'stompjs';
declare module 'sockjsclient';
interface NodeModule {
  id: string;
}
