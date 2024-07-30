import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-checkbox',
  templateUrl: './checkbox.component.html',
  styleUrls: ['./checkbox.component.css'],
})
export class CheckboxComponent {
  @Input() label: string;
  @Output() change = new EventEmitter<boolean>();
  checked = false;

  onCheckboxChange(): void {
    this.checked = !this.checked;
    this.change.emit(this.checked);
  }
}
