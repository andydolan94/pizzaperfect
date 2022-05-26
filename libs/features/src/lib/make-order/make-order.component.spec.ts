import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MakeOrderComponent } from './make-order.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { ActivatedRoute } from '@angular/router';

describe('MakeOrderComponent', () => {
	let component: MakeOrderComponent;
	let fixture: ComponentFixture<MakeOrderComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			declarations: [MakeOrderComponent],
			imports: [
				RouterTestingModule,
				HttpClientTestingModule,
				MatSnackBarModule,
			],
			providers: [
				{
					provide: ActivatedRoute,
					useValue: {
						snapshot: {
							paramMap: {
								get: () => 1, // Mock the route
							},
						},
					},
				},
			],
		}).compileComponents();
	});

	beforeEach(() => {
		fixture = TestBed.createComponent(MakeOrderComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});
});
