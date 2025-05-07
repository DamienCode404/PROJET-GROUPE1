export class Utilisateurs {
    constructor(
      private _id: number,
      private _utilisateurType: string,
      private _login: string,
      private _password: string,
      private _lastName: string,
      private _firstName: string,
      private _email: string,
      private _phoneNumber: string,
      private _imageBase64: string,
      private _tag: string
    ) {}
  
    public get id(): number {
      return this._id;
    }
  
    public set id(value: number) {
      this._id = value;
    }

    public get tag(): string {
      return this._tag;
    }
  
    public set tag(value: string) {
      this._tag = value;
    }
    
    public get utilisateurType(): string {
      return this._utilisateurType;
    }
  
    public set utilisateurType(value: string) {
      this._utilisateurType = value;
    }

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
  
    public get lastName(): string {
      return this._lastName;
    }
  
    public set lastName(value: string) {
      this._lastName = value;
    }
  
    public get firstName(): string {
      return this._firstName;
    }
  
    public set firstName(value: string) {
      this._firstName = value;
    }
  
    public get email(): string {
      return this._email;
    }
  
    public set email(value: string) {
      this._email = value;
    }
  
    public get phoneNumber(): string {
      return this._phoneNumber;
    }
  
    public set phoneNumber(value: string) {
      this._phoneNumber = value;
    }
    public get imageBase64(): string {
      return this._imageBase64;
    }
    public set imageBase64(value: string) {
        this._imageBase64 = value;
    }
}  