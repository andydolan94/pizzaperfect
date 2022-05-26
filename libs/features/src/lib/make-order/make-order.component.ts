import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';
import { PizzaOrderService, PizzaService } from '@pizzaperfect/core';
import { Pizza, PizzaOrder } from '@pizzaperfect/data';

@Component({
	selector: 'pizzaperfect-make-order',
	templateUrl: './make-order.component.html',
	styleUrls: ['./make-order.component.scss'],
})
export class MakeOrderComponent implements OnInit {
	pizzaOrder!: PizzaOrder;

	// State of the component
	state:
		| 'loading'
		| 'ready'
		| 'submitted'
		| 'delivered'
		| 'notfound'
		| 'error' = 'loading';

	// Pizza properties before they're populated
	pizzaId: number | undefined;
	pizzaTopping: string | undefined;
	pizzaBase: string | undefined;
	pizzaSize: string | undefined;
	pizzaNote = '';

	constructor(
		private route: ActivatedRoute,
		private pizzaService: PizzaService,
		private pizzaOrderService: PizzaOrderService,
		private snackBar: MatSnackBar
	) {}

	ngOnInit(): void {
		const id: string | null = this.route.snapshot.paramMap.get('id');
		if (id) {
			this.pizzaOrderService.getPizzaOrder(+id).subscribe({
				next: (pizzaOrder: PizzaOrder) => {
					this.pizzaOrder = pizzaOrder;

					/**
					 * Checks the state of the order to determine 
					 * if it is delivered, submitted, or ready 
					 * for the user to order pizzas
					 */
					if (pizzaOrder.delivered) {
						this.state = 'delivered';
					} else if (pizzaOrder.submitted) {
						this.state = 'submitted';
					} else {
						this.state = 'ready';
					}
				},
				error: (err: HttpErrorResponse) => {
					err.status === 404 ? this.state = 'notfound' : this.state = 'error';
				},
			});
		} else {
			console.error(`Could not get pizza of id: ${id}`);
		}
	}

	/**
	 * Call this function to refresh the pizza order. This is
	 * good to call when you've updated the order,
	 * or added/edited/removed a pizza
	 */
	getPizzaOrder() {
		this.pizzaOrderService
			.getPizzaOrder(this.pizzaOrder.id)
			.subscribe((pizzaOrder: PizzaOrder) => {
				this.pizzaOrder = pizzaOrder;
			});
	}

	/**
	 * Set the topping
	 * @param topping the selected topping
	 */
	onSelectTopping(topping: string) {
		this.pizzaTopping = topping;
	}

	/**
	 * Set the base
	 * @param base the selected base
	 */
	onSelectBase(base: string) {
		this.pizzaBase = base;
	}

	/**
	 * Set the size
	 * @param size the selected size
	 */
	onSelectSize(size: string) {
		this.pizzaSize = size;
	}

	/**
	 * Create a pizza by calling this method. It will use the
	 * topping, base, size, and optional note assigned.
	 */
	onAddPizza() {
		if (this.pizzaTopping && this.pizzaBase && this.pizzaSize) {
			this.pizzaService
				.createPizza({
					topping: this.pizzaTopping,
					base: this.pizzaBase,
					size: this.pizzaSize,
					note: this.pizzaNote,
					pizzaOrderId: this.pizzaOrder.id,
				})
				.subscribe({
					next: () => {
						this.getPizzaOrder();
						this.snackBar.open(`🍕 Pizza added to order`, 'close', {
							duration: 3000,
						});

						// once updated, clear all of the properties
						this.clearData();
					},
					error: (err: HttpErrorResponse) => {
						console.error(err);
						this.snackBar.open(
							`⚠️ Adding pizza failed, please try again later`,
							'close',
							{ duration: 3000 }
						);
					},
				});
		}
	}

	/**
	 * Updates the selected pizza from the populated data
	 */
	onUpdatePizza() {
		if (
			this.pizzaId &&
			this.pizzaTopping &&
			this.pizzaBase &&
			this.pizzaSize
		) {
			this.pizzaService
				.updatePizza(this.pizzaId, {
					topping: this.pizzaTopping,
					base: this.pizzaBase,
					size: this.pizzaSize,
					note: this.pizzaNote,
					pizzaOrderId: this.pizzaOrder.id,
				})
				.subscribe({
					next: () => {
						this.getPizzaOrder();
						this.snackBar.open(
							`🍕 Pizza updated in order`,
							'close',
							{
								duration: 3000,
							}
						);

						// once updated, clear all of the properties
						this.clearData();
					},
					error: (err: HttpErrorResponse) => {
						console.error(err);
						this.snackBar.open(
							`⚠️ Updating pizza failed, please try again later`,
							'close',
							{ duration: 3000 }
						);
					},
				});
		}
	}

	/**
	 * Populates the component data to allow the user to
	 * modify a pizza selected in the order
	 * @param pizza the pizza to modify
	 */
	onModifyPizza(pizza: Pizza) {
		this.pizzaId = pizza.id;
		this.pizzaTopping = pizza.topping;
		this.pizzaBase = pizza.base;
		this.pizzaSize = pizza.size;
		this.pizzaNote = pizza.note;
	}

	/**
	 * Delete a selected pizza
	 * @param id the id of the pizza to delete
	 */
	onDeletePizza(id: number) {
		this.pizzaService.deletePizza(id).subscribe({
			next: () => {
				this.snackBar.open(`🍕 Pizza removed from order`, 'close', {
					duration: 3000,
				});
				this.getPizzaOrder();
			},
			error: (err: HttpErrorResponse) => {
				console.error(err);
				this.snackBar.open(
					`⚠️ Deleting pizza failed, please try again later`,
					'close',
					{ duration: 3000 }
				);
			},
		});
	}

	/**
	 * Submits the order when the user has finished assembling the order
	 */
	onSubmitOrder() {
		this.pizzaOrderService
			.updatePizzaOrder(this.pizzaOrder.id, {
				...this.pizzaOrder,
				submitted: true,
			})
			.subscribe({
				next: (pizzaOrder: PizzaOrder) => {
					this.pizzaOrder = pizzaOrder;
					if (this.pizzaOrder.submitted) {
						this.snackBar.open(`🍕 Order has been submitted`, 'close', {
							duration: 3000,
						});
						this.state = 'submitted';
					}
				},
				error: (err: HttpErrorResponse) => {
					console.error(err);
					this.snackBar.open(
						`⚠️ Updating pizza order failed, please try again later`,
						'close',
						{ duration: 3000 }
					);
				},
			});
	}

	/**
	 * Clears the populated data in the component
	 */
	clearData() {
		this.pizzaId = undefined;
		this.pizzaTopping = undefined;
		this.pizzaBase = undefined;
		this.pizzaSize = undefined;
		this.pizzaNote = '';
	}
}
