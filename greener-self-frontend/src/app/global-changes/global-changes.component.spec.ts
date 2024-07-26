import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GlobalChangesComponent } from './global-changes.component';

describe('GlobalChangesComponent', () => {
  let component: GlobalChangesComponent;
  let fixture: ComponentFixture<GlobalChangesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [GlobalChangesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(GlobalChangesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
