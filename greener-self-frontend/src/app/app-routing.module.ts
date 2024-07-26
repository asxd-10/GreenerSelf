import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { DashboardOverviewComponent } from './dashboard-overview/dashboard-overview.component';
import { OrganizationProfileComponent } from './organization-profile/organization-profile.component';
import { GlobalChangesComponent } from './global-changes/global-changes.component';

const routes: Routes = [
{ path: 'login', component: LoginComponent },
{ path: 'register', component: RegisterComponent },
{ path: '', redirectTo: '/login', pathMatch: 'full' }, // Redirect to login by default
{ path: '**', redirectTo: '/login' }, // Wildcard route for a 404 page (optional)
  { path: 'dashboard-overview', component: DashboardOverviewComponent },
  { path: 'organization-profile', component: OrganizationProfileComponent },
  { path: 'global-changes', component: GlobalChangesComponent },
  { path: '', redirectTo: '/dashboard-overview', pathMatch: 'full' } // Default route
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
