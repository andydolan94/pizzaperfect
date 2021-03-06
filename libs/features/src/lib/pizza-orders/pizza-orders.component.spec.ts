import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PizzaOrdersComponent } from './pizza-orders.component';

describe('PizzaOrdersComponent', () => {
	let component: PizzaOrdersComponent;
	let fixture: ComponentFixture<PizzaOrdersComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			declarations: [PizzaOrdersComponent],
			imports: [HttpClientTestingModule]
		}).compileComponents();
	});

	beforeEach(() => {
		fixture = TestBed.createComponent(PizzaOrdersComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
