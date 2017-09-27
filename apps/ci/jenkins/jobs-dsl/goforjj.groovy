multibranchPipelineJob('goforjj') {
  description('Folder for Project goforjj generated and maintained by Forjj. To update it use forjj update')
  branchSources {
      github {
          apiUri('https://api.github.com')
          includes('*')
          repoOwner('forj-oss')
          repository('goforjj')
      }
  }
  orphanedItemStrategy {
      discardOldItems {
          numToKeep(20)
      }
  }
}
