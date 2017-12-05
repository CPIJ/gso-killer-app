import { User } from "./user";

export class LoginResponse {
    user: User;
    message: String;
    get hasError(): boolean { return this.user === null };
}
