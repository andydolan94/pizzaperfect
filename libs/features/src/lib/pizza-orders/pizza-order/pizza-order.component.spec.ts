import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCardModule } from '@angular/material/card';
import { PizzaOrder } from '@pizzaperfect/data';

import { PizzaOrderComponent } from './pizza-order.component';

describe('PizzaOrderComponent', () => {
	let component: PizzaOrderComponent;
	let fixture: ComponentFixture<PizzaOrderComponent>;
	const pizzaOrder: PizzaOrder = {
		id: 0,
		customerName: 'Test Name',
		deliveryAddress: 'Test Address',
		submitted: true,
		delivered: false,
		pizzas: [],
	};

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			declarations: [PizzaOrderComponent],
			imports: [
				HttpClientTestingModule,
				MatSnackBarModule,
				MatCardModule,
			],
		}).compileComponents();
	});

	beforeEach(() => {
		fixture = TestBed.createComponent(PizzaOrderComponent);
		fixture.componentInstance.pizzaOrder = pizzaOrder;
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
