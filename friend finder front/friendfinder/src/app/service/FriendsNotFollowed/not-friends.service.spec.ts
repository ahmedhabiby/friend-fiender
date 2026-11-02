import { TestBed } from '@angular/core/testing';

import { NotFriendsService } from './not-friends.service';

describe('NotFriendsService', () => {
  let service: NotFriendsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(NotFriendsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
