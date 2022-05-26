import { HttpErrorResponse } from '@angular/common/http';
import { Component, EventEmitter, Input, Output } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { PizzaOrderService } from '@pizzaperfect/core';
import { PizzaOrder } from '@pizzaperfect/data';

@Component({
	selector: 'pizzaperfect-pizza-order',
	templateUrl: './pizza-order.component.html',
	styleUrls: ['./pizza-order.component.scss'],
})
export class PizzaOrderComponent {
	@Input() pizzaOrder!: PizzaOrder;
	@Output() updateRequest = new EventEmitter<void>();

	constructor(private pizzaOrderService: PizzaOrderService, private snackBar: MatSnackBar) {}

	onMarkAsDelivered(pizzaOrder: PizzaOrder) {
		this.pizzaOrderService.updatePizzaOrder(pizzaOrder.id, {
			...pizzaOrder,
			delivered: true,
		}).subscribe({
			next: (pizzaOrder: PizzaOrder) => {
				this.snackBar.open(`🍕 Order marked as delivered! ${pizzaOrder.customerName} will be happy`, 'close', {
					duration: 3000,
				});
				this.updateRequest.emit();
			},
			error: (err: HttpErrorResponse) => {
				console.error(err)
				this.snackBar.open(
					`⚠️ Updating pizza order failed, please try again later`,
					'close',
					{ duration: 3000 }
				);
			}
		});		
	}
}
