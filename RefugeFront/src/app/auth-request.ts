export class AuthRequest {
    constructor(private _login: string, private _password: string, private _rememberMe:boolean) { }

    public get login(): string {
        return this._login;
    }

    public set login(value: string) {
        this._login = value;
    }

    public get password(): string {
        return this._password;
    }

    public set password(value: string) {
        this._password = value;
    }

    public get rememberMe(): boolean {
        return this._rememberMe;
    }

    public set rememberMe(value: boolean) {
        this._rememberMe = value;
    }
}
