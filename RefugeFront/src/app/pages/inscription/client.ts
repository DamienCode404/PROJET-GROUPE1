export class Client {
    constructor(
      private _id: number,
      private _firstName: string,
      private _lastName: string ,
      private _email: string ,
      private _login: string ,
      private _password: string ,
      private _numero: string,
      private _voie: string,
      private _ville: string,
      private _cp: string,
      private _imageBase64: string
    ) {}
  
    public get imageBase64(): string {
      return this._imageBase64;
    }
    public set imageBase64(value: string) {
        this._imageBase64 = value;
    }
  
    public get id(): number {
      return this._id;
    }
  
    public set id(value: number ) {
      this._id = value;
    }
  
    public get firstName(): string {
      return this._firstName;
    }
  
    public set firstName(value: string) {
      this._firstName = value;
    }
  
    public get lastName(): string {
      return this._lastName;
    }
  
    public set lastName(value: string) {
      this._lastName = value;
    }
  
    public get email(): string {
      return this._email;
    }
  
    public set email(value: string) {
      this._email = value;
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
  
    public get numero(): string {
      return this._numero;
    }
  
    public set numero(value: string ) {
      this._numero = value;
    }
  
    public get voie(): string  {
      return this._voie;
    }
  
    public set voie(value: string ) {
      this._voie = value;
    }
  
    public get ville(): string  {
      return this._ville;
    }
  
    public set ville(value: string ) {
      this._ville = value;
    }
  
    public get cp(): string  {
      return this._cp;
    }
  
    public set cp(value: string ) {
      this._cp = value;
    }
  }
  