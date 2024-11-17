import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit{
  
  loading: boolean = false;
  public customers : any = [];

  constructor(private http:HttpClient){
  }
  
  ngOnInit(): void {
    this.loading = true;
    this.http.get("http://localhost:8083/api/customers").subscribe({
      next : data => {
        this.loading = false;
        this.customers = data;
      },
      error : err => {
        this.loading = false;
        console.log(err);
      }
    })

  }

}
