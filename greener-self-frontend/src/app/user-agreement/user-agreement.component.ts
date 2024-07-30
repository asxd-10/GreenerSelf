import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-user-agreement',
  templateUrl: './user-agreement.component.html',
  styleUrls: ['./user-agreement.component.css'],
})
export class UserAgreementComponent implements OnInit {
  currentContent: 'terms' | 'privacy' = 'terms';

  constructor() {}

  ngOnInit(): void {}

  showTerms(): void {
    this.currentContent = 'terms';
  }

  showPrivacyPolicy(): void {
    this.currentContent = 'privacy';
  }
}
