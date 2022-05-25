import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PizzaOrderService } from '@pizzaperfect/core';
import { PizzaOrder } from '@pizzaperfect/data';

@Component({
	selector: 'pizzaperfect-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
	constructor(private router: Router) {}

	public beginOrder() {

		// disable the button

		// this.pizzaOrderService.create().subscribe((pizzaOrder: PizzaOrder) => {
		// 	// Performs creation of an order and then passes
		// 	// the created order into the url
		// 	this.router.navigate([
		// 		'pizza-order',
		// 		pizzaOrder.id,
		// 	]);
		// })
	}
}
