export class Produit {
    public get imageBase64(): string {
        return this._imageBase64;
    }
    public set imageBase64(value: string) {
        this._imageBase64 = value;
    }
    constructor(private _id: number, private _libelle: string, private _description: string, private _prix: number, private _stock: number, private _imageBase64: string) { }
    
    public get id(): number {
        return this._id;
    }

    public set id(value: number) {
        this._id = value;
    }

    public get libelle(): string {
        return this._libelle;
    }

    public set libelle(value: string) {
        this._libelle = value;
    }


    public get description(): string {
        return this._description;
    }

    public set description(value: string) {
        this._description = value;
    }

    public get prix(): number {
        return this._prix;
    }

    public set prix(value: number) {
        this._prix = value;
    }

    public get stock(): number {
        return this._stock;
    }

    public set stock(value: number) {
        this._stock = value;
    }


}
