import { User } from "../model/user";

export default class Context {

    private static _currentUser: User;
    
    static get currentUser(): User {
        return JSON.parse(localStorage.getItem("current-user"));
    }

    static set currentUser(user: User) {
        localStorage.setItem('current-user', JSON.stringify(user));
    }
}