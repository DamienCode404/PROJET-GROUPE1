export class AuthResponse {
    constructor(private _success: boolean, private _token: string, private _idUser: number, private _roleUser: string) { }

    public get success(): boolean {
        return this._success;
    }

    public set success(value: boolean) {
        this._success = value;
    }

    public get token(): string {
        return this._token;
    }

    public set token(value: string) {
        this._token = value;
    }

    public get idUser(): number {
        return this._idUser;
    }

    public set id(value: number) {
        this._idUser = value;
    }

    public get roleUser(): string {
        return this._roleUser;
    }

    public set roleUser(value: string) {
        this._roleUser = value;
    }

}
