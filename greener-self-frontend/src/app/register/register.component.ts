import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';


@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  imports: [FormsModule, HttpClientModule],
  standalone: true,
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
