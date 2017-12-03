export const host = 'http://localhost:8082/syncoder';
export const subscriptions = {
    afterRegisteration: '/topic/after-registration',
    projectOnChange:  '/topic/project/onchange/',
    onClientCountChange: '/topic/project/onClientCountChange/'
}
export const sends = {
    projectOnOpened: '/syncoder/project/onOpened',
    projectChanged: '/syncoder/project/change/'
}