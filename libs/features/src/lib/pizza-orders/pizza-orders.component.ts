import { Component, OnInit } from '@angular/core';
import { PizzaOrderService } from '@pizzaperfect/core';
import { PizzaOrder } from '@pizzaperfect/data';
import { BehaviorSubject, Observable, switchMapTo } from 'rxjs';

@Component({
	selector: 'pizzaperfect-pizza-orders',
	templateUrl: './pizza-orders.component.html',
	styleUrls: ['./pizza-orders.component.scss'],
})
export class PizzaOrdersComponent implements OnInit {
	event$ = new BehaviorSubject(true);
	pizzaOrders$!: Observable<PizzaOrder[]>;

	constructor(private pizzaOrderService: PizzaOrderService) {}

	ngOnInit(): void {
		this.pizzaOrders$ = this.event$.pipe(
			switchMapTo(this.pizzaOrderService.getPizzaOrders())
		);
	}

	updatePizzaOrder() {
		this.event$.next(true);
	}
}
