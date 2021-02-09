import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TitledCardButtonComponent } from './titled-card-button.component';

describe('TitledCardButtonComponent', () => {
  let component: TitledCardButtonComponent;
  let fixture: ComponentFixture<TitledCardButtonComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TitledCardButtonComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TitledCardButtonComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
