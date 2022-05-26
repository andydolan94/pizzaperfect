import { HttpClientTestingModule } from '@angular/common/http/testing';
import { TestBed } from '@angular/core/testing';

import { PizzaService } from './pizza.service';

describe('PizzaService', () => {
	let service: PizzaService;

	beforeEach(() => {
		TestBed.configureTestingModule({
			imports: [HttpClientTestingModule],
		});
		service = TestBed.inject(PizzaService);
	});

	it('should be created', () => {
		expect(service).toBeTruthy();
	});
});
