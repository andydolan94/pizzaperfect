import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PizzaOrderService, PizzaService } from '@pizzaperfect/core';
import { PizzaOrder } from '@pizzaperfect/data';

@Component({
	selector: 'pizzaperfect-make-order',
	templateUrl: './make-order.component.html',
	styleUrls: ['./make-order.component.scss'],
})
export class MakeOrderComponent implements OnInit {
	pizzaOrder!: PizzaOrder;

	// Pizza properties before they're populated
	pizzaId: number | undefined;
	pizzaTopping: string | undefined;
	pizzaBase: string | undefined;
	pizzaSize: string | undefined;
	pizzaNote = "";

	constructor(
		private route: ActivatedRoute,
		private pizzaService: PizzaService,
		private pizzaOrderService: PizzaOrderService
	) {}

	ngOnInit(): void {
		const id: string | null = this.route.snapshot.paramMap.get('id');
		if (id) {
			this.pizzaOrderService
				.getPizzaOrder(+id)
				.subscribe((pizzaOrder: PizzaOrder) => {
					this.pizzaOrder = pizzaOrder;
					console.log(pizzaOrder);
				});
		} else {
			console.error(`Could not get pizza of id: ${id}`);
		}
	}

	onSelectTopping(topping: string) {
		this.pizzaTopping = topping;
	}

	onSelectBase(base: string) {
		this.pizzaBase = base;
	}

	onSelectSize(size: string) {
		this.pizzaSize = size;
	}

	onAddPizza() {
		if (this.pizzaTopping && this.pizzaBase && this.pizzaSize) {
			this.pizzaService.createPizza({ 
				topping: this.pizzaTopping,
				base: this.pizzaBase,
				size: this.pizzaSize,
				note: this.pizzaNote,
				pizzaOrderId: this.pizzaOrder.id
			}).subscribe({
				next: () => {
					console.log("success");
				},
				error: (err: HttpErrorResponse) => {
					console.error(err);
				} 
			})
		}
	}
}
