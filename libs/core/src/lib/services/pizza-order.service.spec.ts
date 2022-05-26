import { TestBed } from '@angular/core/testing';

import { PizzaOrderService } from './pizza-order.service';

import { HttpClientTestingModule } from '@angular/common/http/testing';

describe('PizzaOrderService', () => {
	let service: PizzaOrderService;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [HttpClientTestingModule]
		});
		service = TestBed.inject(PizzaOrderService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});
});
