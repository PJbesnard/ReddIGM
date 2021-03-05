import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListSubsComponent } from './list-subs.component';

describe('ListSubsComponent', () => {
  let component: ListSubsComponent;
  let fixture: ComponentFixture<ListSubsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListSubsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListSubsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
