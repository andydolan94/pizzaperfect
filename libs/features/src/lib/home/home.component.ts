import { HttpErrorResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PizzaOrderService } from '@pizzaperfect/core';
import { PizzaOrder } from '@pizzaperfect/data';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
	selector: 'pizzaperfect-home',
	templateUrl: './home.component.html',
	styleUrls: ['./home.component.scss'],
})
export class HomeComponent {
	pizzaOrderForm = this.fb.group({
		customerName: ['', Validators.required],
		deliveryAddress: ['', Validators.required],
	});

	constructor(
		private router: Router,
		private fb: FormBuilder,
		private pizzaOrderService: PizzaOrderService,
		private snackBar: MatSnackBar,
	) {}

	onSubmit() {
		this.pizzaOrderForm.disable();

		this.pizzaOrderService
			.createPizzaOrder({
				customerName: this.customerName.value,
				deliveryAddress: this.deliveryAddress.value,
				submitted: false,
				delivered: false,
			})
			.subscribe({
				next: (pizzaOrder: PizzaOrder) => {
					// Performs creation of an order and then passes
					// the created order into the url
					this.router.navigate(['make-order', pizzaOrder.id]);
				},
				error: (err: HttpErrorResponse) => {
					this.pizzaOrderForm.enable();
					console.error(err);
					this.snackBar.open(
						`⚠️ Order creation failed, please try again later`,
						'close',
						{ duration: 3000 }
					);
				},
			});
	}

	get customerName() {
		return this.pizzaOrderForm.get('customerName') as FormArray;
	}

	get deliveryAddress() {
		return this.pizzaOrderForm.get('deliveryAddress') as FormArray;
	}
}
