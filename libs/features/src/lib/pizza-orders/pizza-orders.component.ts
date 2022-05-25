import { Component } from '@angular/core';
import { PizzaOrderService } from '@pizzaperfect/core';

@Component({
	selector: 'pizzaperfect-pizza-orders',
	templateUrl: './pizza-orders.component.html',
	styleUrls: ['./pizza-orders.component.scss'],
})
export class PizzaOrdersComponent {
	constructor(private pizzaOrderService: PizzaOrderService) {}
}
