import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BrowseSubsComponent } from './browse-subs.component';

describe('BrowseSubsComponent', () => {
  let component: BrowseSubsComponent;
  let fixture: ComponentFixture<BrowseSubsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BrowseSubsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BrowseSubsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
