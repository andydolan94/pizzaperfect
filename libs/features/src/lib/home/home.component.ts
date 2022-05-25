import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PizzaOrderService } from '@pizzaperfect/core';
import { PizzaOrder } from '@pizzaperfect/data';

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

	constructor(private router: Router, private fb: FormBuilder) {}


		
	onSubmit() {
		this.pizzaOrderForm.disable();

		// this.pizzaOrderService.create().subscribe((pizzaOrder: PizzaOrder) => {
		// 	// Performs creation of an order and then passes
		// 	// the created order into the url
		// 	this.router.navigate([
		// 		'pizza-order',
		// 		pizzaOrder.id,
		// 	]);
		// })
	}

	get customerName() {
		return this.pizzaOrderForm.get('customerName') as FormArray;
	}

	get deliveryAddress() {
		return this.pizzaOrderForm.get('deliveryAddress') as FormArray;
	}
}
