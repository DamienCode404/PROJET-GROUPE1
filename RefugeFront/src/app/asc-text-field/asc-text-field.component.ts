import { Component, forwardRef, Input } from '@angular/core';
import { ControlValueAccessor, NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'asc-text-field',
  standalone: false,
  templateUrl: './asc-text-field.component.html',
  styleUrl: './asc-text-field.component.css',
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => AscTextFieldComponent),
      multi: true
    }
  ]
})
export class AscTextFieldComponent implements ControlValueAccessor {
  @Input() label: string = "";
  value: string = "";

  onChange = (value: any) => { };
  onTouched = () => { };

  writeValue(value: any): void {
    this.value = value;
  }

  registerOnChange(fn: any): void {
    this.onChange = fn;
  }

  registerOnTouched(fn: any): void {
    this.onTouched = fn;
  }

  setDisabledState?(isDisabled: boolean): void { }

  public onInputChange(evt: any) {
    this.value = evt.target.value;
    this.onChange(this.value);
  }
}
