import { Component, Input, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css'],
})
export class NavigationComponent {
  @Input() currentContent: 'terms' | 'privacy';
  @Output() navigate = new EventEmitter<string>();

  navigateToPrivacyPolicy(): void {
    this.navigate.emit('privacy');
  }

  navigateToTerms(): void {
    this.navigate.emit('terms');
  }
}
