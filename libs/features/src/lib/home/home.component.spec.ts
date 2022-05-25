import { ComponentFixture, TestBed } from '@angular/core/testing';


import { HomeComponent } from './home.component';

describe('HomeComponent', () => {
	let component: HomeComponent;
	let fixture: ComponentFixture<HomeComponent>;

	beforeEach(async () => {
		await TestBed.configureTestingModule({
			declarations: [HomeComponent]
		}).compileComponents();
	});

	beforeEach(() => {
		fixture = TestBed.createComponent(HomeComponent);
		component = fixture.componentInstance;
		fixture.detectChanges();
	});

	it('should create', () => {
		expect(component).toBeTruthy();
	});

	it('should have a button to create an order', () => {
		const button = fixture.debugElement.nativeElement.querySelector('button');
		expect(button.textContent).toContain("Begin order");
	})
});
