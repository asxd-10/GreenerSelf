import { Component } from '@angular/core';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService) {}

  onSubmit() {
    this.authService.register(this.username, this.password).subscribe(response => {
      // Handle successful registration
    }, error => {
      // Handle registration error
    });
  }
}
