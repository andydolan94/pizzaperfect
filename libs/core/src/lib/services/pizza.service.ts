import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreatePizzaInput, Pizza, UpdatePizzaInput } from '@pizzaperfect/data';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root',
})
export class PizzaService {
	constructor(private http: HttpClient) {}

	public getPizzas(): Observable<Pizza[]> {
		return this.http.get<Pizza[]>(`/api/pizzas`);
	}

	public getPizza(id: number): Observable<Pizza> {
		return this.http.get<Pizza>(`/api/pizzas/${id}`);
	}

	public createPizza(pizza: CreatePizzaInput): Observable<Pizza> {
		return this.http.post<Pizza>(`/api/pizzas`, pizza);
	}

	public updatePizza(id: number, pizza: UpdatePizzaInput): Observable<Pizza> {
		return this.http.put<Pizza>(`/api/pizzas/${id}`, pizza);
	}

	public deletePizza(id: number): Observable<void> {
		return this.http.delete<void>(`/api/pizzas/${id}`);
	}
}
