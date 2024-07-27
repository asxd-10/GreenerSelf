import { Component } from '@angular/core';
import { AuthService } from '../auth.service';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  imports: [FormsModule, HttpClientModule],
  standalone: true,
  styleUrl: './login.component.css',
  providers: [AuthService],
})
export class LoginComponent {
  username: string = '';
  password: string = '';

  constructor(private authService: AuthService) {}

  onSubmit() {
    this.authService.login(this.username, this.password).subscribe(response => {
      // Handle successful login
    }, error => {
      // Handle login error
    });
  }
}
