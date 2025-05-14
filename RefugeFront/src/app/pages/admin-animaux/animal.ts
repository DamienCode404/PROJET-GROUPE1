export class Animal {

    public idContact!: number;

    constructor(
        private _id: number, 
        private _nom: string, 
        private _race: string, 
        private _naissance: string, 
        private _description: string, 
        private _statut: string, 
        private _idWorker : number|null, 
        private _imageBase64: string
    ) {}

public get imageBase64(): string {
    return this._imageBase64;
}
public set imageBase64(value: string) {
    this._imageBase64 = value;
}

public get id()
{
    return this._id;
}

public set id(value : number)
{
    this._id = value;
}

public get nom()
{
    return this._nom;
}

public set nom(value : string)
{
    this._nom = value;
}

public get race()
{
    return this._race;
}

public set race(value : string)
{
    this._race = value;
}

public get naissance()
{
    return this._naissance;
}

public set naissance(value : string)
{
    this._naissance = value;
}

public get description()
{
    return this._description;
}

public set description(value : string)
{
    this._description = value;
}

public get statut()
{
    return this._statut;
}

public set statut(value : string)
{
    this._statut = value;
}

public get idWorker()
{
    return this._idWorker;
}

public set idWorker(value : number|null)
{
    this._idWorker = value;
}

}
