import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router} from '@angular/router';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrl: './orders.component.css'
})
export class OrdersComponent implements OnInit {
  
  public orders: any;
  
  constructor(private http : HttpClient, private router : Router) {

  }
  
  ngOnInit(): void {
    this.http.get("http://localhost:8088/api/orders").subscribe({
      next : orders => {
        this.orders = orders;
      },
      error : err => {
        console.log(err);
      }
    })
  }

  getOrderDetails(order : any) {
    this.router.navigateByUrl("/order-details/"+order.id);
  }
    
}
