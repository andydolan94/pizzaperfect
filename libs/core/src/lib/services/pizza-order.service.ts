import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {
	CreatePizzaOrderInput,
	PizzaOrder,
	UpdatePizzaOrderInput,
} from '@pizzaperfect/data';

@Injectable({
	providedIn: 'root',
})
export class PizzaOrderService {
	constructor(private http: HttpClient) {}

	public getPizzaOrders(): Observable<PizzaOrder[]> {
		return this.http.get<PizzaOrder[]>(`/api/pizza-orders`);
	}

	public getPizzaOrder(id: number): Observable<PizzaOrder> {
		return this.http.get<PizzaOrder>(`/api/pizza-orders/${id}`);
	}

	public createPizzaOrder(
		pizzaOrder: CreatePizzaOrderInput
	): Observable<PizzaOrder> {
		return this.http.post<PizzaOrder>(`/api/pizza-orders`, pizzaOrder);
	}

	public updatePizzaOrder(
		id: number,
		pizzaOrder: UpdatePizzaOrderInput
	): Observable<PizzaOrder> {
		return this.http.put<PizzaOrder>(`/api/pizza-orders/${id}`, pizzaOrder);
	}
}
