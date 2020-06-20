import { NgModule } from '@angular/core';
import { Routes, RouterModule, CanActivate} from '@angular/router';
import { BarberListComponent } from './barber-list/barber-list.component';
import { BarberDetailsComponent } from './barber-details/barber-details.component';
import { LoginComponent } from "./login/login.component";
import { OrderListComponent } from './order-list/order-list.component';
import { AdminPageComponent } from './admin-page/admin-page.component';
import { OrderDetailsComponent } from './order-details/order-details.component';
import { AuthGuard, AdminAuthGuard } from './services/auth-guard.service';

const routes: Routes = [
  { path: '',   redirectTo: '/barber', pathMatch: 'full' },
  { path: 'barber', component: BarberListComponent},
  { path: 'barber/:id', component: BarberDetailsComponent},
  { path: "login", component: LoginComponent },
  { path: "order", component: OrderListComponent, canActivate: [AuthGuard] },
  { path: "order/:id", component: OrderDetailsComponent, canActivate: [AuthGuard] },
  { path: "admin", component: AdminPageComponent, canActivate: [AdminAuthGuard]  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
