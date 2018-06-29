import { TestBed, inject } from '@angular/core/testing';

import { FamilyCreatorService } from './family-creator.service';

describe('FamilyCreatorService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [FamilyCreatorService]
    });
  });

  it('should be created', inject([FamilyCreatorService], (service: FamilyCreatorService) => {
    expect(service).toBeTruthy();
  }));
});
