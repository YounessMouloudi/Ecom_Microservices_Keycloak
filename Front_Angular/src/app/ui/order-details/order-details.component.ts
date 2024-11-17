import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrl: './order-details.component.css'
})
export class OrderDetailsComponent implements OnInit {
  
  orderDetails : any;
  orderId! : string;

  constructor(private http : HttpClient, private route : ActivatedRoute) {
    this.orderId = this.route.snapshot.params['id'];
  }

  ngOnInit(): void {
    
    this.http.get("http://localhost:8088/api/orders/"+this.orderId).subscribe({
      next : orders => {
        this.orderDetails = orders;
      },
      error : err => {
        console.log(err);
      }
    })
  }

  getTotal(orderDetails: any) {
    let total : number = 0;
    
    orderDetails.productItems.forEach( (pi: any) => {
      total = total + (pi.price * pi.quantity);
    }); 

    return total;
  }
    
}
