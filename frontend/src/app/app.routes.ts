import { Routes } from '@angular/router';
import { CustomerChatComponent } from '../chat/customer/customer-chat.component';
import { EmployeeChatComponent } from '../chat/employee/employee-chat.component';
import { HomeComponent } from '../home/home.component';

export const routes: Routes = [
  {
    path: '',
    component: HomeComponent,
  },
  {
    path: 'customer',
    component: CustomerChatComponent,
  },
  {
    path: 'employee',
    component: EmployeeChatComponent,
  },
];
