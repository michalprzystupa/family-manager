import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddFatherComponent } from './add-father.component';

describe('AddFatherComponent', () => {
  let component: AddFatherComponent;
  let fixture: ComponentFixture<AddFatherComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddFatherComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddFatherComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
